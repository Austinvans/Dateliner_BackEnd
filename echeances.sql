CREATE KEYSPACE IF NOT EXISTS  echeances WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 2 };




---------------------------- TABLE VENDEUR ----------------------------
CREATE TABLE seller (
	seller_id uuid,
	seller_name text,
	seller_surname text,
	seller_store text,
	seller_store_description text,
	PRIMARY KEY (seller_id)
);



----------------------------- TABLE CLIENT -----------------
CREATE TABLE customer (
	customer_id uuid,
	customer_name text,
	customer_surname text,
	customer_tel text,
	customer_email text, 
	PRIMARY KEY (customer_id)
);

----------------------------- TABLE UTILISATEUR -----------------
CREATE TABLE user (
	user_id uuid,
	user_name text,
	user_surname text,
	user_email text, 
	user_password text,
	PRIMARY KEY (user_id)
);

----------------------------- TABLE COMMANDE --------------
CREATE TABLE orders (
	order_id uuid,
	order_amount float,
	order_date timestamp,
	order_description text, 
	PRIMARY KEY (order_id)
);
 
 ---------------------------- TABLE COMMANDES PAR CLIENT ------------
CREATE TABLE orders_by_customer (
	customer_id uuid,
	order_id uuid,
	customer_name text,
	customer_surname text,
	customer_tel text,
	customer_email text,,
	order_amount float,
	order_date timestamp,
	order_description text,
	PRIMARY KEY (customer_id, order_id)
); 
 
 ----------------------------- TABLE ECHEANCIER -----------------------
CREATE TABLE timeliner (
	timeliner_id uuid,
	timeliner_amount float,
	timeliner_start_date timestamp,
	timeliner_end_date timestamp,
	dateliner_number int,
	timeliner_status text,
	timeliner_reminder_number int, 
	PRIMARY KEY (timeliner_id)
);

------------------------ TABLE COMMANDES PAR ECHEANCIER -----------------
CREATE TABLE orders_by_timeliner (
	timeliner_id uuid,
	order_id uuid,
	timeliner_amount float,
	timeliner_start_date timestamp,
	timeliner_end_date timestamp,
	dateliner_number int,
	timeliner_status text,
	timeliner_reminder_number int,
	order_amount float,
	order_date timestamp,
	order_description text, 
	PRIMARY KEY (timeliner_id, order_id)
);


--------------------------- TABLE ECHEANCE --------------------------
CREATE TABLE dateliner (
	dateliner_id uuid,
	dateliner_amount float,
	dateliner_start_date timestamp,
	dateliner_end_date timestamp,
	dateliner_status text,
	dateliner_reminder_time int, 
	PRIMARY KEY (dateliner_id)
);


-------------------- TABLE ECHEANCES PAR ECHEANCIER ---------------------------------
CREATE TABLE dateliner_by_timeliner (
	timeliner_id uuid,
	dateliner_id uuid,
	timeliner_amount float,
	timeliner_start_date timestamp,
	timeliner_end_date timestamp,
	dateliner_number int,
	timeliner_status text,
	timeliner_reminder_number int, 
	dateliner_amount float,
	dateliner_start_date timestamp,
	dateliner_end_date timestamp,
	dateliner_status text,
	dateliner_reminder_time int,
	PRIMARY KEY (timeliner_id, dateliner_id)
);

------------------ TABLE CLIENTS PAR VENDEUR ----------------------------
CREATE TABLE customer_by_seller (
	seller_id uuid,
	customer_id uuid,
	seller_name text,
	seller_surname text,
	seller_store text,
	seller_store_description text,
	customer_name text,
	customer_surname text,
	customer_tel text,
	customer_email text,
	PRIMARY KEY (seller_id, customer_id )
);

----------------- TABLE ECHEANCIERS PAR VENDEUR --------------------------
CREATE TABLE timeliner_by_seller (
	seller_id uuid,
	timeliner_id uuid,
	seller_name text,
	seller_surname text,
	seller_store text,
	seller_store_description text,
	timeliner_amount float,
	timeliner_start_date timestamp,
	timeliner_end_date timestamp,
	dateliner_number int,
	timeliner_status text,
	timeliner_reminder_number int,
	PRIMARY KEY (seller_id, timeliner_id)	
);

----------------- TABLE ECHEANCIERS PAR CLIENT --------------------------
CREATE TABLE timeliner_by_customer (
	customer_id uuid,
	timeliner_id uuid,
	customer_name text,
	customer_surname text,
	customer_tel text,
	customer_email text,
	timeliner_amount float,
	timeliner_start_date timestamp,
	timeliner_end_date timestamp,
	dateliner_number int,
	timeliner_status text,
	timeliner_reminder_number int,
	PRIMARY KEY (customer_id, timeliner_id)	
);

----------- SYNTAXE POUR L'AJOUT D'UN INDEX SUR UNE COLONNE ----------------
create index on customer_by_seller(customer_email);

create index on customer(customer_email);


------------------------------ TABLE FACTURE ---------------------------
CREATE TABLE bills (
	bill_id uuid,
	bill_number int,
	bill_date timestamp,
	PRIMARY KEY (bill_id)
);

------------------ TABLE FACTURES PAR ABONNEMENT --------------------------
CREATE TABLE bills_by_subscription (
	subscription_id uuid,
	bill_id uuid,
	subscription_amount float,
	subscription_duration text,
	subscription_description text,
	subscription_start_date timestamp,
	subscription_end_date timestamp,
	bill_number int,
	bill_date timestamp,
	PRIMARY KEY (subscription_id, bill_id)
);

---------------------------- TABLE ABONNEMENT --------------------------------
CREATE TABLE subscriptions(
	subscription_id uuid,
	subscription_amount float,
	subscription_duration text,
	subscription_description text,
	subscription_start_date timestamp,
	subscription_end_date timestamp,
	PRIMARY KEY (subscription_id)
);


-------------------- TABLE ABONNEMENTS PAR ECHEANCIER --------------------------
CREATE TABLE subscriptions_by_timeliner(
	timeliner_id uuid,
	subscription_id uuid,
	timeliner_amount float,
	timeliner_start_date timestamp,
	timeliner_end_date timestamp,
	dateliner_number int,
	timeliner_status text,
	timeliner_reminder_number int,
	subscription_amount float,
	subscription_duration text,
	subscription_description text,
	subscription_start_date timestamp,
	subscription_end_date timestamp,
	PRIMARY KEY (timeliner_id, subscription_id)
);

----------------------- TABLE PAYEMENT -------------------------------
CREATE TABLE payments (
	payment_id uuid,
	payment_amount float,
	payment_date timestamp,
	payment_status text,
	PRIMARY KEY (payment_id)
);

----------------------- TABLE PAYEMENTS PAR FACTURE ----------------------------------
CREATE TABLE payments_by_bill (
	bill_id uuid,
	payment_id uuid,
	bill_number int,
	bill_date timestamp,
	payment_amount float,
	payment_date timestamp,
	payment_status text,
	PRIMARY KEY (bill_id, payment_id)
);

------------------------ TABLE MOYENS DE PAYEMENT -------------------------
CREATE TABLE payment_methods (
	payment_mode_id uuid,
	payment_mode_name text,
	payment_mode_description text,
	PRIMARY KEY (payment_mode_id)
);

----------------- TABLE PAYEMENTS PAR MOYEN DE PAYEMENT -------------------------------
CREATE TABLE payments_by_payment_method (
	payment_mode_id uuid,
	payment_id uuid,
	payment_amount float,
	payment_date timestamp,
	payment_status text,
	payment_mode_name text,
	payment_mode_description text,
	PRIMARY KEY (payment_mode_id, payment_id)
);

---------------------------------- TABLE RELANCE --------------------------------
CREATE TABLE reminder (
	reminder_id uuid,
	reminder_message text,
	reminder_status text,
	reminder_date timestamp,
	PRIMARY KEY (reminder_id)
);

---------------------------- TABLE MOYENS DE RELANCE ---------------------------
CREATE TABLE reminder_means (
	reminder_mean_id uuid,
	reminder_mean_name text,
	reminder_mean_description text,
	PRIMARY KEY (reminder_mean_id)
);

---------------------------- TABLE MONNAIE ---------------------------
CREATE TABLE currency (
	currency_id uuid,
	currency_name text,
	currency_description text,
	PRIMARY KEY (currency_id)
);

-------------------- TABLE RELANCES PAR MOYEN DE RELANCE ---------------------------
CREATE TABLE reminder_by_reminder_means (
	reminder_id uuid,
	reminder_mean_id uuid,
	reminder_message text,
	reminder_status text,
	reminder_date timestamp,
	reminder_mean_name text,
	reminder_mean_description text,
	PRIMARY KEY (reminder_mean_id, reminder_id)
);

------------------- TABLE MOYENS DE RELANCES PAR ECHEANCIER ---------------------------
CREATE TABLE reminder_means_by_timeliner (
	timeliner_id uuid,
	reminder_mean_id uuid,
	timeliner_amount float,
	timeliner_start_date timestamp,
	timeliner_end_date timestamp,
	dateliner_number int,
	timeliner_status text,
	timeliner_reminder_number int,
	reminder_mean_name text,
	reminder_mean_description text,
	PRIMARY KEY (timeliner_id, reminder_mean_id)
);

------------------- TABLE objets garants PAR ECHEANCIER ---------------------------
CREATE TABLE guarantee_objects_by_timeliner (
	timeliner_id uuid,
	guarantee_id uuid,
	timeliner_name text,
	timeliner_amount float,
	guarantee_label text,
	guarantee_amount float,
	guarantee_image text,
	PRIMARY KEY (timeliner_id, guarantee_id)
);

----------------------- TABLE ABONNEMENTS PAR VENDEUR -------------------------
CREATE TABLE subscriptions_by_seller(
	seller_id uuid,
	subscription_id uuid,
	seller_name text,
	seller_surname text,
	seller_store text,
	seller_store_description text,
	subscription_amount float,
	subscription_duration text,
	subscription_description text,
	subscription_start_date timestamp,
	subscription_end_date timestamp,
	PRIMARY KEY (seller_id, subscription_id)
);



--alter table timeliner add timeliner_name text;
-- cqlsh:echeances> create index on orders_by_timeliner(timeliner_amount);
-- cqlsh:echeances> create index on orders_by_timeliner(timeliner_start_date);
-- cqlsh:echeances> create index on orders_by_timeliner(timeliner_end_date);
-- cqlsh:echeances> create index on orders_by_timeliner(timeliner_reminder_number);
-- cqlsh:echeances> create index on orders_by_timeliner(dateliner_number);
-- cqlsh:echeances> create index on orders_by_timeliner(order_amount);
-- cqlsh:echeances> create index on orders_by_timeliner(order_date);
-- cqlsh:echeances> create index on orders_by_timeliner(order_description);






