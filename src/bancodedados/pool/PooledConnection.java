package bancodedados.pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author Pedro Abs
 * pedro_abs@yahoo.com.br
 * https://pedroabs.com/2011/09/27/connection-pool-em-java-pool-de-conexoes/
 * Esta classe é um Decorator para a conexão real que implementa a interface Connection.
 * 
 * Observe o método close. Ao invés de fechar a conexão a devolvemos para o pool.
 */
public class PooledConnection implements Connection, Comparable<PooledConnection> {
	
	private Connection conn;
	private ConnectionPool pool;
	private long creationTime;
	private double id = Math.round(Math.random()*10000000);
	private boolean available = true;
	private long lastUsed;
	
	/**
	 * 
	 * @param conn Um objeto que implemente a interface Connection também.
	 * @param pool O objeto pool.
	 */
	public PooledConnection(Connection conn, ConnectionPool pool){
		this.conn = conn;
		this.pool = pool;
		this.creationTime = System.currentTimeMillis();
	}
	


	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return this.conn.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.conn.isWrapperFor(iface);
	}

	@Override
	public Statement createStatement() throws SQLException {
		return this.conn.createStatement();
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return this.conn.prepareStatement(sql);
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return this.conn.prepareCall(sql);
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return this.conn.nativeSQL(sql);
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		this.conn.setAutoCommit(autoCommit);
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return this.conn.getAutoCommit();
	}

	@Override
	public void commit() throws SQLException {
		this.conn.commit();
	}

	@Override
	public void rollback() throws SQLException {
		this.conn.rollback();
	}

	/**
	 * Ao invés de fechar a conexão simplesmente a devolvemos para
	 * o pool.
	 */
	@Override
	public void close() throws SQLException {
		//System.out.println("devolvendo conexão " + this);
		this.pool.checkIn(this);
	}
	
	/**
	 * Fecha verdadeiramente a conexão que foi "decorada" nesta classe.
	 * @throws SQLException
	 */
	public void reallyClose() throws SQLException{
		this.conn.close();
	}
	
	@Override
	public boolean isClosed() throws SQLException {
		return this.conn.isClosed();
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return this.conn.getMetaData();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		this.conn.setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return this.conn.isReadOnly();
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		this.conn.setCatalog(catalog);
	}

	@Override
	public String getCatalog() throws SQLException {
		return this.conn.getCatalog();
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		this.conn.setTransactionIsolation(level);
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return this.conn.getTransactionIsolation();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return this.conn.getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		this.conn.clearWarnings();
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return this.conn.createStatement(resultSetType, resultSetConcurrency);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return this.conn.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return this.conn.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return this.conn.getTypeMap();
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		this.conn.setTypeMap(map);
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		this.conn.setHoldability(holdability);
	}

	@Override
	public int getHoldability() throws SQLException {
		return this.conn.getHoldability();
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return this.conn.setSavepoint();
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return this.conn.setSavepoint(name);
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		this.conn.rollback();
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		this.conn.releaseSavepoint(savepoint);
	}

	@Override
	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return this.conn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return this.conn.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return this.conn.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		return this.conn.prepareStatement(sql, autoGeneratedKeys);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {
		return this.conn.prepareStatement(sql, columnIndexes);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		return this.conn.prepareStatement(sql, columnNames);
	}

	@Override
	public Clob createClob() throws SQLException {
		return this.conn.createClob();
	}

	@Override
	public Blob createBlob() throws SQLException {
		return this.conn.createBlob();
	}

	@Override
	public NClob createNClob() throws SQLException {
		return this.conn.createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return this.conn.createSQLXML();
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return this.conn.isValid(timeout);
	}

	@Override
	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
		this.conn.setClientInfo(name, value);

	}

	@Override
	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {
		this.conn.setClientInfo(properties);
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return this.conn.getClientInfo(name);
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return this.conn.getClientInfo();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		return this.conn.createArrayOf(typeName, elements);
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		return this.conn.createStruct(typeName, attributes);
	}
	
	@Override
	public boolean equals(Object obj) {
		//return this.conn.equals(obj);
				
		if(obj instanceof PooledConnection){
			PooledConnection other = (PooledConnection)obj;
			if(this.getCreationTime() == other.creationTime && 
					this.id == other.id) return true;
		}
		return false;
		
	}
	
	@Override
	public int hashCode() {
		//return this.conn.hashCode();
		return new Double(this.id).hashCode() + new Long(this.creationTime).hashCode();
	}
	
	@Override
	public String toString() {
		return "Conexão " + new Double(this.id).toString();
		//return new Long(this.lastUsed).toString();
	}
	
	public long getAge(){
		long now = System.currentTimeMillis();
		return now - this.creationTime;
	}

	protected void setAvailable(boolean available) {
		this.available = available;
	}
	

	public boolean isAvailable() {
		return available;
	}

	protected void setLastUsed(long lastUsed) {
		this.lastUsed = lastUsed;
	}

	public long getLastUsed() {
		return lastUsed;
	}
	
	public long getCreationTime(){
		return this.creationTime;
	}



	@Override
	public int compareTo(PooledConnection o) {
		Long esse = Long.valueOf(this.creationTime);
		Long outro = Long.valueOf(o.creationTime);
		return esse.compareTo(outro);
	}

    @Override
    public void setSchema(String schema) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSchema() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
