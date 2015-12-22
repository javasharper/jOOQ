/**
 * Copyright (c) 2009-2015, Data Geekery GmbH (http://www.datageekery.com)
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: http://www.jooq.org/licenses
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.jooq.example.vertabelo;

import static java.util.Arrays.asList;
import static org.jooq.impl.DSL.countDistinct;
import static org.jooq.example.vertabelo.db.h2.Tables.AUTHOR;
import static org.jooq.example.vertabelo.db.h2.Tables.BOOK;
import static org.jooq.example.vertabelo.db.h2.Tables.BOOK_STORE;
import static org.jooq.example.vertabelo.db.h2.Tables.BOOK_TO_BOOK_STORE;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.jooq.example.vertabelo.db.h2.tables.Author;
import org.jooq.example.vertabelo.db.h2.tables.Book;
import org.jooq.example.vertabelo.db.h2.tables.BookStore;
import org.jooq.example.vertabelo.db.h2.tables.BookToBookStore;
import org.jooq.example.vertabelo.db.h2.tables.records.BookRecord;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Lukas Eder
 */
public class QueryTest {

    private static Connection connection;

    @BeforeClass
    public static void before() throws Exception {
        Properties properties = new Properties();
        properties.load(QueryTest.class.getResourceAsStream("/config.properties"));

        Class.forName(properties.getProperty("db.driver"));
        connection = DriverManager.getConnection(
            properties.getProperty("db.url"),
            properties.getProperty("db.username"),
            properties.getProperty("db.password")
        );
    }

    @Test
    public void testJoin() throws Exception {
        // All of these tables were generated by jOOQ's Maven plugin
        Book b = BOOK.as("b");
        Author a = AUTHOR.as("a");
        BookStore s = BOOK_STORE.as("s");
        BookToBookStore t = BOOK_TO_BOOK_STORE.as("t");

        Result<Record3<String, String, Integer>> result =
        DSL.using(connection)
              .select(a.FIRST_NAME, a.LAST_NAME, countDistinct(s.NAME))
              .from(a)
              .join(b).on(b.AUTHOR_ID.equal(a.ID))
              .join(t).on(t.BOOK_ID.equal(b.ID))
              .join(s).on(t.NAME.equal(s.NAME))
              .groupBy(a.FIRST_NAME, a.LAST_NAME)
              .orderBy(countDistinct(s.NAME).desc())
              .fetch();

        assertEquals(2, result.size());
        assertEquals("Paulo", result.getValue(0, a.FIRST_NAME));
        assertEquals("George", result.getValue(1, a.FIRST_NAME));

        assertEquals("Coelho", result.getValue(0, a.LAST_NAME));
        assertEquals("Orwell", result.getValue(1, a.LAST_NAME));

        assertEquals(Integer.valueOf(3), result.getValue(0, countDistinct(s.NAME)));
        assertEquals(Integer.valueOf(2), result.getValue(1, countDistinct(s.NAME)));
    }

    @Test
    public void testActiveRecords() throws Exception {
        Result<BookRecord> result = DSL.using(connection).selectFrom(BOOK).orderBy(BOOK.ID).fetch();

        assertEquals(4, result.size());
        assertEquals(asList(1, 2, 3, 4), result.getValues(0));
    }
}