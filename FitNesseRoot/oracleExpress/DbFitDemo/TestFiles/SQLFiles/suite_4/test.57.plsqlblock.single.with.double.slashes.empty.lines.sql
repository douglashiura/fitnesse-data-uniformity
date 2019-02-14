declare
begin
  insert into test_emp (id, description) 
  SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
      'single with double slash' || sysdate FROM DUAL;
  commit;
end;
/



/




