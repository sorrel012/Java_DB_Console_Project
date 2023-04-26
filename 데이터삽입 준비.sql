delete from tblkospi;
delete from tblkosdaq;

select count(*) from tblkospi;
select count(*) from tblkosdaq;

ALTER system SET processes=100000 scope=spfile;
COMMIT;
