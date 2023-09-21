package service.table

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class TableClienteServiceTest {

    private lateinit var clienteService: TableClienteService
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

        clienteService = TableClienteService()
        clienteService.connection = mockConnection
    }

    @Test
    fun testAddClienteValid() {
        `when`(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement)
        `when`(mockPreparedStatement.executeUpdate()).thenReturn(1)
        `when`(mockPreparedStatement.generatedKeys).thenReturn(mockResultSet)
        `when`(mockResultSet.next()).thenReturn(true)
        `when`(mockResultSet.getInt(1)).thenReturn(1)

        val clienteService = TableClienteService()
        clienteService.connection = mockConnection

        val clienteId = clienteService.addCliente("João", "12345678901", "Rua A")

        verify(mockPreparedStatement, times(1)).executeUpdate()
        verify(mockResultSet, times(1)).next()
        verify(mockResultSet, times(1)).getInt(1)

        assert(clienteId == 1)
    }


    @Test
    fun testAddClienteNotValid() {
        `when`(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement)
        `when`(mockPreparedStatement.executeUpdate()).thenReturn(0)

        val clienteId = clienteService.addCliente(" ", " ", " ")

        verify(mockPreparedStatement, times(1)).executeUpdate()
        verify(mockResultSet, never()).next()

        assert(clienteId == -1)
    }

    @Test
    fun testDeleteClienteValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(1)

        clienteService.deleteCliente(1)

        verify(mockStatement, times(1)).executeUpdate(anyString())
    }

    @Test
    fun testDeleteClienteNotValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(0)

        clienteService.deleteCliente(-1)

        verify(mockStatement, never()).executeUpdate(anyString())
    }

    @Test
    fun testListClientes() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet)

        `when`(mockResultSet.next()).thenReturn(true, true, false)
        `when`(mockResultSet.getInt("id")).thenReturn(1, 2)
        `when`(mockResultSet.getString("nome")).thenReturn("Alice", "Bob")
        `when`(mockResultSet.getString("cpf")).thenReturn("12345678901", "9876543210")
        `when`(mockResultSet.getString("endereco")).thenReturn("Rua A", "Rua B")

        clienteService.listClientes()

        verify(mockStatement, times(1)).executeQuery(anyString())
        verify(mockResultSet, times(2)).getInt("id")
        verify(mockResultSet, times(2)).getString("nome")
        verify(mockResultSet, times(2)).getString("cpf")
        verify(mockResultSet, times(2)).getString("endereco")
    }


}