begin
  insert into test_emp (id, description) 
  SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
      'test_51' FROM DUAL;
  commit;
end;

/


begin
  insert into test_emp (id, description) 
  SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
      'test_51' FROM DUAL;
  commit;
end;
/