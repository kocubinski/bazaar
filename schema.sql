SET SCHEMA 'public';

CREATE TABLE size
(
  id CHAR(5),
  CONSTRAINT pk_size PRIMARY KEY (id)
);

CREATE TABLE stat
(
  id CHAR(4),
  CONSTRAINT pk_stat PRIMARY KEY (id)
);

CREATE TABLE race
(
  id CHAR(3),
  CONSTRAINT pk_race PRIMARY KEY (id)
);

CREATE TABLE class
(
  id CHAR(3),
  CONSTRAINT pk_class PRIMARY KEY (id)
);

CREATE TABLE slot
(
  id CHAR(9),
  CONSTRAINT pk_slot PRIMARY KEY (id)
);

CREATE TABLE modifier
(
  id CHAR(8),
  CONSTRAINT pk_modifier PRIMARY KEY (id)
);

CREATE TABLE item
(
  id SERIAL,
  name VARCHAR(256),
  weight DECIMAL NOT NULL,
  size CHAR(5) NOT NULL,
  CONSTRAINT pk_item PRIMARY KEY (id),
  CONSTRAINT ak_site UNIQUE (name),
  CONSTRAINT fk_item_size FOREIGN KEY (size) REFERENCES size(id)
);

CREATE TABLE item_charge
(
  item_id INT,
  amount INT NOT NULL,
  CONSTRAINT pk_item_charge PRIMARY KEY (item_id),
  CONSTRAINT fk_item_charge_item FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE item_effect
(
  item_id INT,
  raw VARCHAR(512) NOT NULL,
  CONSTRAINT pk_item_effect PRIMARY KEY (item_id),
  CONSTRAINT fk_item_effect FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE item_stat
(
  item_id INT,
  stat CHAR(4),
  modifier INT NOT NULL,
  CONSTRAINT pk_item_stat PRIMARY KEY (item_id, stat),
  CONSTRAINT fk_item_stat_item FOREIGN KEY (item_id) REFERENCES item(id),
  CONSTRAINT fk_item_stat_stat FOREIGN KEY (stat) REFERENCES stat(id)
);

CREATE TABLE item_race
(
  item_id INT,
  race CHAR(3),
  CONSTRAINT pk_item_stat PRIMARY KEY (item_id, race),
  CONSTRAINT fk_item_race_item FOREIGN KEY (item_id) REFERENCES item(id),
  CONSTRAINT fk_item_race_race FOREIGN KEY (race) REFERENCES race(id)
);

CREATE TABLE item_slot
(
  item_id INT,
  slot CHAR(9),
  CONSTRAINT pk_item_slot PRIMARY KEY (item_id, slot),
  CONSTRAINT fk_item_slot_item FOREIGN KEY (item_id) REFERENCES item(id),
  CONSTRAINT fk_item_slot_slot FOREIGN KEY (slot) REFERENCES slot(id)
);

CREATE TABLE item_modifier
(
  item_id INT,
  modifier CHAR(8),
  CONSTRAINT pk_item_modifier PRIMARY KEY (item_id, modifier),
  CONSTRAINT fk_item_modifier_item FOREIGN KEY (item_id) REFERENCES item(id),
  CONSTRAINT fk_item_modifier_modifier FOREIGN KEY (modifier) REFERENCES modifier(id)
);
