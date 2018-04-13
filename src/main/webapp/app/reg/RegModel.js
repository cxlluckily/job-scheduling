Ext.define('Dcs.reg.RegModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'string'
	}, {
		name : 'name',
		type : 'string'
	}, {
		name : 'source_id',
		type : 'string'
	}, {
		name : 'db_name',
		type : 'string'
	}, {
		name : 'table_name',
		type : 'string'
	}, {
		name : 'col_name',
		type : 'string'
	}, {
		name : 'col_value',
		type : 'string'
	}, {
		name : 'operator',
		type : 'string'
	}, {
		name : 'sql_txt',
		type : 'string'
	}, {
		name : 'create_time',
		type : 'date'
	} ]
});