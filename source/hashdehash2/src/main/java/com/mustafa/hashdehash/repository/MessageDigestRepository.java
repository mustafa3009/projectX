package com.mustafa.hashdehash.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mustafa.hashdehash.domain.MessageDigest;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;


public class MessageDigestRepository {

    private static final String TABLE_NAME = "messagedigest";

    private Session session;

    public MessageDigestRepository(Session session) {
        this.session = session;
    }

    /**
     * Creates the message digest table.
     */
    public void createTable() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(").append("digest text PRIMARY KEY,").append("message text);");

        final String query = sb.toString();
        session.execute(query);
    }

    
    /**
     * Insert a row in the table books. 
     * 
     * @param book
     */
    public void insertmsgDigest(MessageDigest md) {
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append("(digest,message)").append("VALUES ('").append(md.getDigest()).append("','").append(md.getMessage()).append("');");

        final String query = sb.toString();
        System.out.println("Executing query:" + query);
        session.execute(query);
    }


 

    /**
     * Fetch the message for a given digest.
     * 
     * @return
     */
    public MessageDigest getMessage(String digest) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE digest = '").append(digest).append("';");

        final String query = sb.toString();

        ResultSet rs = session.execute(query);

        List<MessageDigest> md = new ArrayList<MessageDigest>();

        for (Row r : rs) {
            MessageDigest s = new MessageDigest(r.getString("message"), r.getString("digest"));
            md.add(s);
        }

        return md.get(0);
    }

    /**
     * Select all books from books
     * 
     * @return
     */
    public List<MessageDigest> selectAll() {
        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);

        final String query = sb.toString();
        ResultSet rs = session.execute(query);

        List<MessageDigest> mdl = new ArrayList<MessageDigest>();

        for (Row r : rs) {
        	 MessageDigest s = new MessageDigest(r.getString("message"), r.getString("digest"));
            mdl.add(s);
        }
        return mdl;
    }

   
   /**
     * Delete table.
     * 
     * @param tableName the name of the table to delete.
     */
    public void deleteTable(String tableName) {
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);

        final String query = sb.toString();
        session.execute(query);
    }
}
