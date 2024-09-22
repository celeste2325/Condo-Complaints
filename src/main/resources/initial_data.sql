CREATE
DATABASE condo_complaints;
GO

use
condo_complaints
CREATE TABLE buildings
(
    buildingID int          not null identity(1,1),
    name       varchar(100) not null,
    address    varchar(100) not null,
    constraint pk_buildings primary key (buildingID)
);

CREATE TABLE people
(
    document   varchar(20)                not null,
    name       varchar(100)               not null,
    role       varchar(20) default 'user' not null,
    credential varchar(60)
        constraint pk_people primary key (document),
);

CREATE TABLE units
(
    unitID     int         not null identity,
    floor      varchar(5)  not null,
    number     varchar(10) not null,
    occupied   varchar(1)  not null default 'N',
    buildingID int,
    constraint pk_units primary key (unitID),
    constraint fk_units_buildings foreign key (buildingID) references buildings
);

CREATE TABLE owners
(
    id       int identity,
    unitID   int         not null,
    document varchar(20) not null,
    constraint pk_owners primary key (id),
);

CREATE TABLE tenants
(
    id       int identity,
    unitID   int         not null,
    document varchar(20) not null,
    constraint pk_tenants primary key (id),
);

CREATE TABLE complaints
(
    complaintID int         not null identity,
    document    varchar(20) not null,
    buildingID  int         not null,
    location    varchar(300),
    description varchar(1000),
    unitID      int,
    status      varchar(50) default 'new'
        constraint pk_complaints primary key (complaintID),
    constraint fk_complaint_people foreign key (document) references people,
    constraint fk_complaint_building foreign key (buildingID) references buildings,
);

CREATE TABLE images
(
    id          int          not null identity,
    path        varchar(300) not null,
    extension   varchar(10),
    complaintID int,
    constraint pk_images primary key (id),
    constraint fk_image_complaint foreign key (complaintID) references complaints
);

INSERT INTO buildings (name, address)
VALUES (N'SLS Puerto Madero', N'Mogliani 425');
INSERT INTO buildings (name, address)
VALUES (N'The Link Towers', N'Mogliani 426');
INSERT INTO buildings (name, address)
VALUES (N'The Fire Place', N'Mogliani 430');
INSERT INTO buildings (name, address)
VALUES (N'Alvear Tower', N'Mogliani 431');
INSERT INTO buildings (name, address)
VALUES (N'Dique Dos', N'Libertador 990');
INSERT INTO buildings (name, address)
VALUES (N'Libertador Plaza', N'Libertador 5600');

INSERT INTO people (document, name, credential)
VALUES (N'DNI30722275', N'STACUL, RICARDO', '$2a$10$KLOJUQP18WTd1FIRhWu.rueF3qf41PuhcOG4rbm9IxziOy/B9J16C');
INSERT INTO people (document, name)
VALUES (N'DNI30724625', N'AGUIRRE, JAVIER');
INSERT INTO people (document, name)
VALUES (N'DNI30724661', N'FILHO, MARCELO');
INSERT INTO people (document, name)
VALUES (N'DNI30724804', N'CRUZ, PABLO');
INSERT INTO people (document, name)
VALUES (N'DNI30732736', N'MEDINA, CRISTIAN');
INSERT INTO people (document, name)
VALUES (N'DNI30733306', N'CARDOZO, MIGUEL');
INSERT INTO people (document, name)
VALUES (N'DNI30733857', N'ABAD, JUAN');
INSERT INTO people (document, name)
VALUES (N'DNI30734053', N'RAMIREZ, DARIO');
INSERT INTO people (document, name)
VALUES (N'DNI30734099', N'MAGUNA, CRISTIAN');
INSERT INTO people (document, name, credential)
VALUES (N'DNI30734108', N'PETRINO, RICARDO', '$2a$10$KLOJUQP18WTd1FIRhWu.ru8NzwrVdqvObURd1Jt.TrZbBxGV31FCe');
INSERT INTO people (document, name, role, credential)
VALUES (N'CPA3449614', N'BRITEZ, BLAS', 'admin', '$2a$10$KLOJUQP18WTd1FIRhWu.ru8NzwrVdqvObURd1Jt.TrZbBxGV31FCe');
INSERT INTO people (document, name)
VALUES (N'DNI30744673', N'RIGGIO, JOSE');
INSERT INTO people (document, name)
VALUES (N'DNI30745281', N'OLAS, JORGE GUSTAVO');
INSERT INTO people (document, name)
VALUES (N'DNI30746040', N'AYALA, PABLO');
INSERT INTO people (document, name)
VALUES (N'DNI30778776', N'GARCIA, JAVIER');
INSERT INTO people (document, name)
VALUES (N'DNI30780521', N'BEGUET, ADRIAN');
INSERT INTO people (document, name)
VALUES (N'DNI30797973', N'TORRES, CARLOS');
INSERT INTO people (document, name)
VALUES (N'DNI30800519', N'ROMERO, MAURICIO');
INSERT INTO people (document, name, credential)
VALUES (N'DNI30814171', N'ROBLES, MATIAS', '$2a$10$UrNwS5N5r3cSIu6pFhpiVeMfrmxG5DKS3TQXBDHuAHc0YlfX9nTGO');
INSERT INTO people (document, name)
VALUES (N'DNI30816148', N'BARRIL, PABLO');
INSERT INTO people (document, name)
VALUES (N'DNI30852718', N'ALMUA, DIEGO');
INSERT INTO people (document, name)
VALUES (N'DNI30853507', N'ARIAS, MARIANO');
INSERT INTO people (document, name)
VALUES (N'DNI30866787', N'GUTIERREZ, FACUNDO');
INSERT INTO people (document, name)
VALUES (N'DNI30868066', N'SANDOVAL, LEANDRO');
INSERT INTO people (document, name)
VALUES (N'DNI30868857', N'MENGOCHEA, OSCAR');
INSERT INTO people (document, name)
VALUES (N'DNI30868883', N'GARCIA, MATIAS');

INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'7', N'3', N'N', 1);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'6', N'2', N'N', 1);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'5', N'1', N'N', 1);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'10', N'3', N'N', 1);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'10', N'2', N'N', 1);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'10', N'1', N'N', 1);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'9', N'6', N'N', 1);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'9', N'5', N'N', 1);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'9', N'4', N'N', 1);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'9', N'3', N'N', 1);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'9', N'2', N'N', 1);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'9', N'1', N'N', 1);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'8', N'6', N'N', 1);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'25', N'5', N'N', 2);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'25', N'4', N'N', 2);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'25', N'3', N'N', 2);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'25', N'2', N'N', 2);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'25', N'1', N'N', 2);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'24', N'6', N'N', 2);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'24', N'5', N'N', 2);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'24', N'4', N'N', 2);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'30', N'9', N'N', 3);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'30', N'8', N'N', 3);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'30', N'7', N'N', 3);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'30', N'6', N'N', 3);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'30', N'5', N'N', 3);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'30', N'4', N'N', 3);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'9', N'6', N'N', 4);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'9', N'5', N'N', 4);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'9', N'4', N'N', 4);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'9', N'3', N'N', 4);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'9', N'2', N'N', 4);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'1', N'5', N'N', 5);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'1', N'4', N'N', 5);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'1', N'3', N'N', 5);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'1', N'2', N'N', 5);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'1', N'1', N'N', 5);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'8', N'5', N'N', 6);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'8', N'4', N'N', 6);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'8', N'3', N'N', 6);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'8', N'2', N'N', 6);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'8', N'1', N'N', 6);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'7', N'6', N'N', 6);
INSERT INTO units (floor, number, occupied, buildingID)
VALUES (N'7', N'5', N'N', 6);

INSERT INTO owners (unitID, document)
VALUES (1, N'DNI30744673');
INSERT INTO owners (unitID, document)
VALUES (2, N'DNI30746040');
INSERT INTO owners (unitID, document)
VALUES (3, N'DNI30778776');
INSERT INTO owners (unitID, document)
VALUES (4, N'DNI30780521');
INSERT INTO owners (unitID, document)
VALUES (5, N'DNI30797973');
INSERT INTO owners (unitID, document)
VALUES (6, N'DNI30800519');
INSERT INTO owners (unitID, document)
VALUES (7, N'DNI30722275');
INSERT INTO owners (unitID, document)
VALUES (8, N'DNI30724625');
INSERT INTO owners (unitID, document)
VALUES (9, N'DNI30724661');
INSERT INTO owners (unitID, document)
VALUES (10, N'DNI30816148');
INSERT INTO owners (unitID, document)
VALUES (11, N'DNI30814171');
INSERT INTO owners (unitID, document)
VALUES (12, N'DNI30744673');
INSERT INTO owners (unitID, document)
VALUES (13, N'DNI30744673');
INSERT INTO owners (unitID, document)
VALUES (14, N'DNI30853507');
INSERT INTO owners (unitID, document)
VALUES (15, N'DNI30866787');
INSERT INTO owners (unitID, document)
VALUES (17, N'DNI30868066');
INSERT INTO owners (unitID, document)
VALUES (18, N'DNI30868857');
INSERT INTO owners (unitID, document)
VALUES (19, N'DNI30868883');

INSERT INTO tenants (unitID, document)
VALUES (7, N'DNI30722275');
INSERT INTO tenants (unitID, document)
VALUES (8, N'DNI30724625');
INSERT INTO tenants (unitID, document)
VALUES (9, N'DNI30724661');
INSERT INTO tenants (unitID, document)
VALUES (10, N'DNI30724804');
INSERT INTO tenants (unitID, document)
VALUES (11, N'DNI30732736');
INSERT INTO tenants (unitID, document)
VALUES (12, N'DNI30733306');
INSERT INTO tenants (unitID, document)
VALUES (13, N'DNI30733857');
INSERT INTO tenants (unitID, document)
VALUES (14, N'DNI30734053');
INSERT INTO tenants (unitID, document)
VALUES (15, N'DNI30734099');
INSERT INTO tenants (unitID, document)
VALUES (16, N'DNI30734108');
INSERT INTO tenants (unitID, document)
VALUES (17, N'CPA3449614');
INSERT INTO tenants (unitID, document)
VALUES (18, N'DNI30722275');
INSERT INTO tenants (unitID, document)
VALUES (43, N'DNI30814171');

INSERT INTO complaints (document, buildingID, location, description, unitID)
VALUES (N'DNI30722275', 1, N'Parking Area',
        N'Inappropriate Parking: I frequently find that the vehicle from apartment 104 is occupying my assigned parking space. This has become a significant inconvenience for me, as I often have to search for parking in undesignated areas, disrupting my daily routine.',
        7);
INSERT INTO complaints (document, buildingID, location, description, unitID)
VALUES (N'DNI30722275', 1, N'Kitchen Floor',
        N'Floor Damage: I have noticed water leaks coming from apartment 304. The leakage has caused my kitchen floor to warp and develop bubbles in the laminate, creating a tripping hazard and potentially leading to significant repair costs.',
        7);
INSERT INTO complaints (document, buildingID, location, description, unitID)
VALUES (N'DNI30734108', 2, N'Bathroom Ceiling',
        N'Ceiling Leaks: I have been experiencing water leaks in the ceiling of my bathroom. This issue has become more frequent after the recent rains, with water constantly dripping and causing moisture stains on the walls, creating an unhealthy environment. I am worried about possible structural damage.',
        16);
INSERT INTO complaints (document, buildingID, location, description, unitID)
VALUES (N'DNI30734108', 2, N'Bedroom Walls',
        N'Mold Issues: I have noticed mold appearing on the walls of my bedroom, likely due to water leaks from apartment 201. I have been experiencing increased respiratory problems and allergies because of the humidity and mold.',
        16);
INSERT INTO complaints (document, buildingID, location, description, unitID)
VALUES (N'DNI30814171', 6, N'Elevator',
        N'Lack of Maintenance in Common Areas: I have been frustrated that the buildings elevator has been out of service for weeks. This has caused difficulties for tenants living on the upper floors. The lack of access to the elevator has forced some residents, especially those who are elderly or have mobility issues, to use the stairs.',
        43);
INSERT INTO complaints (document, buildingID, location, description, unitID)
VALUES (N'DNI30814171', 6, N'Building Hallways',
        N'Insufficient Lighting in Hallways: I have concerns about the lack of proper lighting in the hallways of the building. There have been several incidents where residents have tripped due to the darkness, raising safety concerns, especially at night.',
        43);

INSERT INTO images (path, extension, complaintID)
VALUES (N'assets//1//issue_parking_area', N'jpeg', 1);
INSERT INTO images (path, extension, complaintID)
VALUES (N'assets//2//issue_floor_damage', N'jpg', 2);
INSERT INTO images (path, extension, complaintID)
VALUES (N'assets//3//issue_ceiling_leaks', N'jpg', 3);
INSERT INTO images (path, extension, complaintID)
VALUES (N'assets//4//mold_issues', N'jpg', 4);
INSERT INTO images (path, extension, complaintID)
VALUES (N'assets//5//elevator_issue', N'jpg', 5);
INSERT INTO images (path, extension, complaintID)
VALUES (N'assets//6//insufficient_lighting', N'jpeg', 6);


