
-- comment #1
insert into test_emp (id, description) 
SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
    'test_53'  FROM DUAL
/
/* comment #2 */
insert into test_emp (id, description) 
SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
    'test_53'  FROM DUAL
/
-- #3 and now commit
commit
/