COPY app_user FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/app_user.csv' CSV ENCODING 'windows-1251' HEADER;
COPY role FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/role.csv' CSV ENCODING 'windows-1251' HEADER;
COPY app_user_role FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/app_user_role.csv' CSV ENCODING 'windows-1251' HEADER;
COPY dashboard FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/dashboard.csv' CSV ENCODING 'windows-1251' HEADER;
COPY dashboard_layout FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/dashboard_layout.csv' CSV ENCODING 'windows-1251' HEADER;
COPY dashboard_details FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/dashboard_details.csv' CSV ENCODING 'windows-1251' HEADER;
COPY project_info FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/project_info.csv' CSV ENCODING 'windows-1251' HEADER;
COPY project_settings FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/project_settings.csv' CSV ENCODING 'windows-1251' HEADER;
COPY reference FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/reference.csv' CSV ENCODING 'windows-1251' HEADER;
COPY attachement FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/attachement.csv' CSV ENCODING 'windows-1251' HEADER;
COPY security_object FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/security_object.csv' CSV ENCODING 'windows-1251' HEADER;
COPY security_permission FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/security_permission.csv' CSV ENCODING 'windows-1251' HEADER;
COPY id_config FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/id_config.csv' CSV ENCODING 'windows-1251' HEADER;
COPY workflow_template FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/workflow_template.csv' CSV ENCODING 'windows-1251' HEADER;
COPY app_user_settings FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/app_user_settings.csv' CSV ENCODING 'windows-1251' HEADER;
COPY app_user_settings_template FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/app_user_settings_template.csv' CSV ENCODING 'windows-1251' HEADER;
COPY storage_service FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/storage_service.csv' CSV ENCODING 'windows-1251' HEADER;
COPY storage_service_parameter FROM 'D:/cccdev/apps/ccc-construct/apps/material-requisition-api/src/main/resources/config/liquibase/data/new-project/storage_service_parameter.csv' CSV ENCODING 'windows-1251' HEADER;
create or replace function bytea_import(p_path text, p_result out bytea)
                   language plpgsql as $$
declare
  l_oid oid;
begin
  select lo_import(p_path) into l_oid;
  select lo_get(l_oid) INTO p_result;
  perform lo_unlink(l_oid);
end;$$;



UPDATE public.attachement set type='CompanyLogo' , data=  bytea_import('D:\cccdev\apps\ccc-construct\apps\material-requisition-api\src\main\resources\images\ccc-logo.png')
where id=1;

UPDATE public.attachement set type='ClientLogo' ,  data=  bytea_import('D:\cccdev\apps\ccc-construct\apps\material-requisition-api\src\main\resources\images\ctjv-logo.png')
where id=2;

