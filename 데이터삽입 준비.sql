delete from tblkospi;
delete from tblkosdaq;

select count(*) from tblkospi;
select count(*) from tblkosdaq;

ALTER system SET processes=100000 scope=spfile;
COMMIT;

create or replace view vwKospiRise
as
select * from tblkospi
    where (SUBSTR(rate, 1, 1) = '+')
        order by SUBSTR(rate, 2) desc;
        
        
create or replace view vwKospiFall
as
select * from tblkospi
    where (SUBSTR(rate, 1, 1) = '-')
        order by SUBSTR(rate, 2) desc;

create or replace view vwKosdaqRise
as
select * from tblkosdaq
    where (SUBSTR(rate, 1, 1) = '+')
        order by SUBSTR(rate, 2) desc;
        
        
create or replace view vwKosdaqFall
as
select * from tblkosdaq
    where (SUBSTR(rate, 1, 1) = '-')
        order by SUBSTR(rate, 2) desc;        
        
        
        
        
        