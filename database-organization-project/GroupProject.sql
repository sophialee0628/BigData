-- DROP SCHEMA "Group Project";

CREATE SCHEMA "Group Project" AUTHORIZATION postgres;

-- DROP TYPE "Group Project"."_customer";

CREATE TYPE "Group Project"."_customer" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = "Group Project".customer,
	DELIMITER = ',');

-- DROP TYPE "Group Project"."_employee";

CREATE TYPE "Group Project"."_employee" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = "Group Project".employee,
	DELIMITER = ',');

-- DROP TYPE "Group Project"."_inventory";

CREATE TYPE "Group Project"."_inventory" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = "Group Project".inventory,
	DELIMITER = ',');

-- DROP TYPE "Group Project"."_login";

CREATE TYPE "Group Project"."_login" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = "Group Project".login,
	DELIMITER = ',');

-- DROP TYPE "Group Project"."_model";

CREATE TYPE "Group Project"."_model" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = "Group Project".model,
	DELIMITER = ',');

-- DROP TYPE "Group Project"."_ordername";

CREATE TYPE "Group Project"."_ordername" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = "Group Project".ordername,
	DELIMITER = ',');
-- "Group Project".customer definition

-- Drop table

-- DROP TABLE "Group Project".customer;

CREATE TABLE "Group Project".customer (
	firstname varchar(20) NULL,
	lastname varchar(20) NULL,
	customerid int4 NOT NULL,
	streetaddress varchar(20) NULL,
	apartmentnumber varchar NULL,
	city varchar(20) NULL,
	state varchar(20) NULL,
	zipcode int4 NULL,
	phonenumber int4 NULL,
	email varchar(25) NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (customerid)
);


-- "Group Project".employee definition

-- Drop table

-- DROP TABLE "Group Project".employee;

CREATE TABLE "Group Project".employee (
	employeeid int4 NOT NULL,
	firstname varchar(20) NOT NULL,
	lastname varchar(20) NOT NULL,
	ssn int4 NULL,
	salary numeric(6,2) NULL,
	paymenttype varchar(20) NULL,
	jobtype varchar(20) NULL,
	CONSTRAINT employee_pkey PRIMARY KEY (employeeid)
);


-- "Group Project".inventory definition

-- Drop table

-- DROP TABLE "Group Project".inventory;

CREATE TABLE "Group Project".inventory (
	id varchar(20) NOT NULL,
	leadtime int4 NULL,
	categorytype varchar(20) NULL,
	quantity int4 NULL,
	department varchar(20) NULL,
	CONSTRAINT inventory_pkey PRIMARY KEY (id)
);


-- "Group Project".login definition

-- Drop table

-- DROP TABLE "Group Project".login;

CREATE TABLE "Group Project".login (
	userid varchar(20) NOT NULL,
	privilege varchar(20) NOT NULL,
	logintime date NULL,
	logouttime date NULL,
	CONSTRAINT login_pkey PRIMARY KEY (userid)
);


-- "Group Project".model definition

-- Drop table

-- DROP TABLE "Group Project".model;

CREATE TABLE "Group Project".model (
	modelnumber varchar(20) NOT NULL,
	saleprice int4 NULL,
	modelname varchar(20) NULL,
	CONSTRAINT model_pkey PRIMARY KEY (modelnumber)
);


-- "Group Project".ordername definition

-- Drop table

-- DROP TABLE "Group Project".ordername;

CREATE TABLE "Group Project".ordername (
	ordernumber varchar(20) NOT NULL,
	customerid int4 NULL,
	employeeid int4 NULL,
	modelname varchar(20) NULL,
	salevalue int4 NULL,
	ordertime date NULL,
	CONSTRAINT ordername_pkey PRIMARY KEY (ordernumber),
	CONSTRAINT ordername_customerid_fkey FOREIGN KEY (customerid) REFERENCES "Group Project".customer(customerid),
	CONSTRAINT ordername_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES "Group Project".employee(employeeid),
	CONSTRAINT ordername_modelname_fkey FOREIGN KEY (modelname) REFERENCES "Group Project".model(modelnumber)
);


