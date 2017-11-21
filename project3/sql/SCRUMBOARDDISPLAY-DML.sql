INSERT INTO LANE VALUES (1, 1, 'To Do');
INSERT INTO LANE VALUES (2, 1, 'In Progress');
INSERT INTO LANE VALUES (3, 1, 'Testing');
INSERT INTO LANE VALUES (4, 1, 'Verify');
INSERT INTO LANE VALUES (5, 1, 'Done');
INSERT INTO LANE VALUES (6, 2, 'Testing');
commit;
/

INSERT INTO CARD VALUES (1,1,0,4,'User can view profile','');
INSERT INTO CARD VALUES (2,1,0,5,'User can update profile','');
INSERT INTO CARD VALUES (3,2,0,5,'User can view boards','');
INSERT INTO CARD VALUES (4,2,0,5,'User can add boards','');
INSERT INTO CARD VALUES (5,3,0,2,'User can logout','');
INSERT INTO CARD VALUES (6,5,1,2,'User can login','');
commit;
/