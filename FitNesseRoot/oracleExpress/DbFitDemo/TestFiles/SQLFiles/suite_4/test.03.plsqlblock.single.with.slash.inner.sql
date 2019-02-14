declare
begin
    DBMS_OUTPUT.PUT_LINE('single block with slash / sign within test. it should not make it fail');

    insert into test_emp (id, description) 
    SELECT nvl( (SELECT MAX(id) FROM test_emp),0)+1, 
        'single with inner slash' || sysdate FROM DUAL;
    commit;
end;
/