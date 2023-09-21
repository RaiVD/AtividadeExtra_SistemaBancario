package service.table

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import service.TipoConta
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class TableContaBancariaServiceTest {

    private lateinit var contaBancariaService: TableContaBancariaService
    private lateinit var mockConnection: Connection
    private lateinit var mockStatement: Statement
    private lateinit var mockPreparedStatement: PreparedStatement
    private lateinit var mockResultSet: ResultSet

    @BeforeEach
    fun setUp() {
        mockConnection = mock(Connection::class.java)
        mockStatement = mock(Statement::class.java)
        mockPreparedStatement = mock(PreparedStatement::class.java)
        mockResultSet = mock(ResultSet::class.java)

        contaBancariaService = TableContaBancariaService()
        contaBancariaService.connection = mockConnection
    }

    @Test
    fun testAddContaBancariaValid() {
        `when`(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement)
        `when`(mockPreparedStatement.executeUpdate()).thenReturn(1)

        contaBancariaService.addContaBancaria(1, TipoConta.CORRENTE, 1234)

        verify(mockPreparedStatement, times(1)).executeUpdate()
    }

    @Test
    fun testAddContaBancariaNotValid() {
        `when`(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement)
        `when`(mockPreparedStatement.executeUpdate()).thenReturn(0)

        contaBancariaService.addContaBancaria(0, TipoConta.POUPANCA, 5678)

        verify(mockPreparedStatement, never()).executeUpdate()
    }

    @Test
    fun testDeleteContaBancariaValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(1)

        contaBancariaService.deleteContaBancaria(1)

        verify(mockStatement, times(1)).executeUpdate(anyString())
    }

    @Test
    fun testDeleteContaBancariaNotValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(0)

        contaBancariaService.deleteContaBancaria(-1)

        verify(mockStatement, never()).executeUpdate(anyString())
    }
}
