create table licenses (
  license_id text primary key,
  description text,
  organization_id text not null,
  product_name text not null,
  license_type text not null,
  comment text
);