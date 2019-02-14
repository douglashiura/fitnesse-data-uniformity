declare
begin
    RAISE_APPLICATION_ERROR (-20002, 'Test error');
end;
/