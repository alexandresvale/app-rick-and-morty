package com.alexandresvale.rickandmorty.feature.characters.impl.data

import androidx.paging.PagingSource
import com.alexandresvale.core.network.NetworkClient
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

internal class CharactersPagingSourceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: CharactersService
    private lateinit var pagingSource: CharactersPagingSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        // Criamos um cliente rápido e passamos para o nosso NetworkClient
        val fastOkHttpClient = okhttp3.OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()

        val retrofit = NetworkClient.createRetrofit(
            baseUrl = mockWebServer.url("/").toString(),
            customClient = fastOkHttpClient
        )

        apiService = retrofit.create(CharactersService::class.java)
        pagingSource = CharactersPagingSource(apiService)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should load page correctly when server returns successful JSON`() = runTest {
        // Given
        val jsonResponse = this.javaClass.classLoader?.getResource("characters_page_1.json")?.readText()
            ?: throw IllegalArgumentException("Arquivo JSON não encontrado!")
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(jsonResponse))

        // 2. When
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = 1, loadSize = 20, placeholdersEnabled = false)
        )

        // Then
        assertTrue(result is PagingSource.LoadResult.Page)
        val page = result as PagingSource.LoadResult.Page
        assertEquals(1, page.data.size)
        assertEquals("Rick Sanchez", page.data.first().name)
        assertEquals(2, page.nextKey)
    }

    @Test
    fun `should return Error when server returns HTTP 500`() = runTest {
        // Given
        mockWebServer.enqueue(MockResponse().setResponseCode(500))

        // When
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = 1, loadSize = 20, placeholdersEnabled = false)
        )

        // When
        assertTrue(result is PagingSource.LoadResult.Error)
        val errorResult = result as PagingSource.LoadResult.Error
        assertTrue(errorResult.throwable is HttpException)
    }

    @Test
    fun `should return Error when there is no internet connection`() = runTest {
        // Given Arrange: Simulamos a internet caindo antes mesmo de conectar
        mockWebServer.enqueue(MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START))

        // When
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = 1, loadSize = 20, placeholdersEnabled = false)
        )

        // Then
        assertTrue(result is PagingSource.LoadResult.Error)
        val errorResult = result as PagingSource.LoadResult.Error
        assertTrue(errorResult.throwable is java.io.IOException)
    }

    @Test
    fun `should return Error on timeout when server stops responding`() = runTest {
        // Given / Arrange.
        mockWebServer.enqueue(MockResponse().setSocketPolicy(SocketPolicy.NO_RESPONSE))

        // When / Act
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = 1, loadSize = 20, placeholdersEnabled = false)
        )

        // Then / Assert
        assertTrue(result is PagingSource.LoadResult.Error)
        val errorResult = result as PagingSource.LoadResult.Error

        // Timeout gera uma SocketTimeoutException
        assertTrue(errorResult.throwable is SocketTimeoutException)
    }

    @Test
    fun `should return Error when DNS resolution fails (UnknownHostException)`() = runTest {
        // Arrange: Criamos um Retrofit de mentira com MockK só para este teste
        val badApiService = mockk<CharactersService>()
        // Ensinamos o dublê a jogar a exata exceção que você viu no emulador!
        coEvery {
            badApiService.getCharacters(any())
        } throws UnknownHostException("Unable to resolve host rickandmortyapi.com")
        // Instanciamos um PagingSource isolado usando o serviço quebrado
        val badPagingSource = CharactersPagingSource(badApiService)

        // Act
        val result = badPagingSource.load(
            PagingSource.LoadParams.Refresh(key = 1, loadSize = 20, placeholdersEnabled = false)
        )

        // Assert
        assertTrue(result is PagingSource.LoadResult.Error)
        val errorResult = result as PagingSource.LoadResult.Error
        // Verificamos se o Paging 3 aguentou a porrada do DNS e encapsulou o erro
        assertTrue(errorResult.throwable is UnknownHostException)
    }
}
