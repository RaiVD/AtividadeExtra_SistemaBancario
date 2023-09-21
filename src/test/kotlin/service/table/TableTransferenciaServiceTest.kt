package service.table

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class TableTransferenciaServiceTest {

    private lateinit var transferenciaService: TableTransferenciaService
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

        transferenciaService = TableTransferenciaService()
        transferenciaService.connection = mockConnection
    }

    @Test
    fun testAddTransferenciaValid() {
        `when`(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement)
        `when`(mockPreparedStatement.executeUpdate()).thenReturn(1)

        transferenciaService.addTransferencia(100.0, 1, 2)

        verify(mockPreparedStatement, times(1)).executeUpdate()
    }

    @Test
    fun testAddTransferenciaNotValid() {
        `when`(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement)
        `when`(mockPreparedStatement.executeUpdate()).thenReturn(0)

        transferenciaService.addTransferencia(0.0, 1, 2)

        verify(mockPreparedStatement, never()).executeUpdate()
    }

    @Test
    fun testDeleteTransferenciaValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(1)

        transferenciaService.deleteTransferencia(1)

        verify(mockStatement, times(1)).executeUpdate(anyString())
    }

    @Test
    fun testDeleteTransferenciaNotValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(0)

        transferenciaService.deleteTransferencia(-1)

        verify(mockStatement, never()).executeUpdate(anyString())
    }
}
