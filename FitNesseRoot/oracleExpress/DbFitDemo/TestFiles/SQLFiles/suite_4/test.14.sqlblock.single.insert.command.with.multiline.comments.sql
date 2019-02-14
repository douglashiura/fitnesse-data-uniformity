/* 
  here script with multiline comments
*/
insert into test_emp (id, description) 
SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
    'single with multiline comments' || sysdate FROM DUAL
    
/* 
  here script with multiline comments
*/
