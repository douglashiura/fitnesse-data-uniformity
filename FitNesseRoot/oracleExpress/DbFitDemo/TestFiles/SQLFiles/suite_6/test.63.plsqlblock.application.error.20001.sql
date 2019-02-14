declare
begin
    RAISE_APPLICATION_ERROR (-20001, 'Test error');
end;
/