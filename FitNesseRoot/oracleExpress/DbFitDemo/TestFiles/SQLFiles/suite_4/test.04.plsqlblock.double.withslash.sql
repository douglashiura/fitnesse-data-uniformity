begin
  insert into test_emp (id, description) 
  SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
      'double with slash #1' || sysdate FROM DUAL;
  commit;
end;

/


begin
  insert into test_emp (id, description) 
  SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
      'double with slash #2' || sysdate FROM DUAL;
  commit;
end;
/