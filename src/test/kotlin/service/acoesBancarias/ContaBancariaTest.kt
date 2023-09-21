package service.acoesBancarias

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class ContaBancariaTest {

    private lateinit var contaBancaria: ContaBancaria
    private lateinit var mockConnection: Connection
    private lateinit var mockPreparedStatement: PreparedStatement
    private lateinit var mockResultSet: ResultSet

    @BeforeEach
    fun setUp() {
        mockConnection = mock(Connection::class.java)
        mockPreparedStatement = mock(PreparedStatement::class.java)
        mockResultSet = mock(ResultSet::class.java)

        contaBancaria = ContaBancaria()
        contaBancaria.connection = mockConnection
    }

    @Test
    fun testVerificarSaldoValid() {
        `when`(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement)
        `when`(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet)
        `when`(mockResultSet.next()).thenReturn(true)
        `when`(mockResultSet.getDouble("saldo")).thenReturn(1000.0)

        val saldo = contaBancaria.verificarSaldo(1)

        verify(mockResultSet, times(1)).getDouble("saldo")
        assert(saldo == 1000.0)
    }

    @Test
    fun testRealizarDepositoValid() {
        `when`(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement)
        `when`(mockPreparedStatement.executeUpdate()).thenReturn(1)
        `when`(mockPreparedStatement.generatedKeys).thenReturn(mockResultSet)
        `when`(mockResultSet.next()).thenReturn(true)
        `when`(mockResultSet.getInt(1)).thenReturn(1)

        val depositoSucesso = contaBancaria.realizarDeposito(1, 500.0)

        verify(mockPreparedStatement, times(1)).executeUpdate()
        verify(mockResultSet, times(1)).next()
        verify(mockResultSet, times(1)).getInt(1)
        assert(depositoSucesso)
    }

    @Test
    fun testRealizarDepositoNotValid() {
        `when`(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement)
        `when`(mockPreparedStatement.executeUpdate()).thenReturn(0)

        val depositoSucesso = contaBancaria.realizarDeposito(1, -100.0)

        verify(mockPreparedStatement, times(1)).executeUpdate()
        verify(mockResultSet, never()).next()
        assert(!depositoSucesso)
    }

    @Test
    fun testRealizarSaqueValid() {
        `when`(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement)
        `when`(mockPreparedStatement.executeUpdate()).thenReturn(1)

        val saldoAtual = contaBancaria.verificarSaldo(1)

        val saqueSucesso = contaBancaria.realizarSaque(1, saldoAtual ?: 0.0)

        verify(mockPreparedStatement, times(1)).executeUpdate()
        assert(saqueSucesso)
    }

    @Test
    fun testRealizarSaqueNotValid() {
        `when`(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement)
        `when`(mockPreparedStatement.executeUpdate()).thenReturn(0)

        val saqueSucesso = contaBancaria.realizarSaque(1, 1000.0)

        verify(mockPreparedStatement, times(1)).executeUpdate()
        assert(!saqueSucesso)
    }
}
