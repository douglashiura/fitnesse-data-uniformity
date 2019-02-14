begin
  -- block 1
  insert into test_emp (id, description) 
  SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
      'tripple block #1 ' || sysdate FROM DUAL;
  commit;
end;

/


begin
  -- block 2
  insert into test_emp (id, description) 
  SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
      'tripple block #2' || sysdate FROM DUAL;
  commit;
end;

/


begin
  -- block 3
  insert into test_emp (id, description) 
  SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
      'tripple block #3' || sysdate FROM DUAL;
  commit;
end;
/