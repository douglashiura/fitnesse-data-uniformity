/* 
  lack of slash between blocks should result in 
  ORA-06550: line 6, column 1:
  PLS-00103: Encountered the symbol "BEGIN" 
  06550. 00000 -  "line %s, column %s:\n%s"
  *Cause:    Usually a PL/SQL compilation error.
  *Action:

*/

set serveroutput on;
begin
  insert into test_emp (id, description) 
  SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
      'double block without slash 1' || sysdate FROM DUAL
  commit;
end;


begin
  insert into test_emp (id, description) 
  SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
      'double block without slash 2' || sysdate FROM DUAL
  commit;
end;
/