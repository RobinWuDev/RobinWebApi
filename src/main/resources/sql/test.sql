--
-- Table structure for table `jay_album`
--

DROP TABLE IF EXISTS jay_album;
CREATE TABLE jay_album (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL ,
  name varchar(255) NOT NULL,
  year varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

--
-- Table structure for table `jay_mingyan`
--

DROP TABLE IF EXISTS jay_mingyan;
CREATE TABLE jay_mingyan (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL ,
  author varchar(255) NOT NULL,
  content varchar(255) NOT NULL,
  category_id INTEGER  NOT NULL,
  PRIMARY KEY (id)
);

--
-- Table structure for table `jay_mingyan_category`
--

DROP TABLE IF EXISTS jay_mingyan_category;
CREATE TABLE jay_mingyan_category (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL ,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
);


--
-- Table structure for table `jay_music`
--

DROP TABLE IF EXISTS jay_music;
CREATE TABLE jay_music (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL ,
  album_id INTEGER NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

