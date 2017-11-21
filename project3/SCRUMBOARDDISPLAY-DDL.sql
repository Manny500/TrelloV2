--Lookup table
CREATE TABLE CARD
  (
    C_ID INT,
    L_ID INT,
    C_VERIFY INT,
    C_WORTH INT,
    C_TITLE VARCHAR2(4000) UNIQUE,
    C_DESCRIPTION VARCHAR2(4000),
    PRIMARY KEY(C_ID)
  );
/
--Lookup table 1-Pending, 2-Approved, 3-Denied
CREATE TABLE TASK
  (
    T_ID   INT,
    C_ID INT,
    T_COMPLETE INT,
    T_INFO VARCHAR2(4000),
    PRIMARY KEY(T_ID)
  );
/
CREATE TABLE LANE
  (
    L_ID INT,
    B_ID INT,
    L_TITLE VARCHAR2(4000) UNIQUE,
  PRIMARY KEY(L_ID)
  ); 
/
CREATE TABLE BOARD
  (
    B_ID INT,
    TV2_ID INT,
    B_TOTAL INT,  
    B_TITLE VARCHAR2(4000) UNIQUE,
    TV2_TEAM INT DEFAULT 0,
  PRIMARY KEY(B_ID)
  );
/
CREATE SEQUENCE card_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE board_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE lane_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE task_seq START WITH 1 INCREMENT BY 1;
/
create or replace TRIGGER board2_seq_trg BEFORE
  INSERT ON board FOR EACH ROW BEGIN IF :new.B_ID = 0  THEN
  SELECT board_seq.NEXTVAL INTO :new.B_ID FROM dual;
END IF;
END;
/
CREATE OR REPLACE TRIGGER card_seq_trg BEFORE
  INSERT ON card FOR EACH ROW BEGIN IF :new.C_ID IS NULL THEN
  SELECT card_seq.NEXTVAL INTO :new.C_ID FROM dual;
END IF;
END;
/
CREATE OR REPLACE TRIGGER lane_seq_trg BEFORE
  INSERT ON lane FOR EACH ROW BEGIN IF :new.L_ID IS NULL OR :new.L_ID = 0 THEN
  SELECT lane_seq.NEXTVAL INTO :new.L_ID FROM dual;
END IF;
END;
/
CREATE OR REPLACE TRIGGER task_seq_trg BEFORE
  INSERT ON task FOR EACH ROW BEGIN IF :new.T_ID IS NULL THEN
  SELECT task_seq.NEXTVAL INTO :new.T_ID FROM dual;
END IF;
END;
/
CREATE OR REPLACE TRIGGER board_seq_trg BEFORE
  INSERT ON board FOR EACH ROW BEGIN IF :new.B_ID IS NULL THEN
  SELECT board_seq.NEXTVAL INTO :new.B_ID FROM dual;
END IF;
END;
/
COMMIT;