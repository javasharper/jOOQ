/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_2698 extends org.jooq.impl.TableImpl<org.jooq.test.h2.generatedclasses.tables.records.T_2698Record> {

	private static final long serialVersionUID = 2072069763;

	/**
	 * The singleton instance of <code>PUBLIC.T_2698</code>
	 */
	public static final org.jooq.test.h2.generatedclasses.tables.T_2698 T_2698 = new org.jooq.test.h2.generatedclasses.tables.T_2698();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.h2.generatedclasses.tables.records.T_2698Record> getRecordType() {
		return org.jooq.test.h2.generatedclasses.tables.records.T_2698Record.class;
	}

	/**
	 * The column <code>PUBLIC.T_2698.ID</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.T_2698Record, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), T_2698, "");

	/**
	 * The column <code>PUBLIC.T_2698.XX</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.T_2698Record, java.lang.Integer> XX = createField("XX", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), T_2698, "");

	/**
	 * No further instances allowed
	 */
	private T_2698() {
		this("T_2698", null);
	}

	private T_2698(java.lang.String alias, org.jooq.Table<org.jooq.test.h2.generatedclasses.tables.records.T_2698Record> aliased) {
		this(alias, aliased, null);
	}

	private T_2698(java.lang.String alias, org.jooq.Table<org.jooq.test.h2.generatedclasses.tables.records.T_2698Record> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.jooq.test.h2.generatedclasses.Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.T_2698Record> getPrimaryKey() {
		return org.jooq.test.h2.generatedclasses.Keys.PK_T_2698;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.T_2698Record>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.T_2698Record>>asList(org.jooq.test.h2.generatedclasses.Keys.PK_T_2698);
	}
}
