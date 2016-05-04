create table if not exists axis (
  id int(16) not null primary key,
  x int not null,
  y int not null
);

create table if not exists boundaries (
  id int(16) not null primary key,
  upper_left_id int(16) not null,
  bottom_right_id int(16) not null,
  foreign key(upper_left_id) references axis(id),
  foreign key(bottom_right_id) references axis(id)
);

create table if not exists province (
  id int(16) not null primary key,
  name varchar(100) not null,
  boundaries_id int(16) not null,
  foreign key(boundaries_id) references boundaries(id)
);

create table if not exists property (
  id int(16) not null primary key,
  axis_id int(16) not null,
  beds int not null,
  baths int not null,
  square_meters int not null,
  foreign key(axis_id) references axis(id)
);