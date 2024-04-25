create table "User"
(
    id_user           serial
        constraint "User_pk"
            primary key,
    id_role           integer   default 2     not null
        constraint "User_Role_id_role_fk"
            references "Role",
    login             varchar(255)            not null,
    email             varchar(255)            not null,
    password_hash     varchar(255)            not null,
    registration_date timestamp default now() not null
);


create table "UserProfile"
(
    id_profile     serial
        constraint "UserProfile_pk"
            primary key,
    id_user        integer not null
        constraint "UserProfile_User_id_user_fk"
            references "User",
    avatar         bytea,
    name           varchar(255),
    email_public   varchar(255),
    about          text,
    date_birthday  date,
    id_social_link integer
        constraint "UserProfile_SocialLink_id_social_link_fk"
            references "SocialLink",
    location       varchar(255)
);

create table "Role"
(
    id_role   serial
        constraint "Role_pk"
            primary key,
    role_name varchar(255) not null
);

create table "List"
(
    id_list       serial
        constraint "List_pk"
            primary key,
    list_name     varchar(255) not null,
    id_creator    integer      not null
        constraint "List_User_id_user_fk"
            references "User",
    creation_date date         not null
);

create table "ListRole"
(
    id_role_list   serial
        constraint "ListRole_pk"
            primary key,
    role_list_name varchar(255) not null
);

create table "Gift"
(
    id_gift          serial
        constraint "Gift_pk"
            primary key,
    id_list          integer               not null
        constraint "Gift_List_id_list_fk"
            references "List",
    gift_name        varchar(255)          not null,
    gift_image       bytea,
    gift_description text,
    id_type          integer               not null
        constraint "Gift_GiftType_id_type_fk"
            references "GiftType",
    gift_added_by    integer               not null
        constraint "Gift_User_id_user_fk"
            references "User",
    gift_added_date  date                  not null,
    is_archived      boolean default false not null,
    is_completed     boolean default false not null
);

create table "GiftType"
(
    id_type   serial
        constraint "GiftType_pk"
            primary key,
    type_name varchar(255) not null
);

create table "RoleListAssignment"
(
    id_assignment serial
        constraint "RoleListAssignment_pk"
            primary key,
    id_user       integer not null
        constraint "RoleListAssignment_User_id_user_fk"
            references "User",
    id_list       integer not null
        constraint "RoleListAssignment_List_id_list_fk"
            references "List",
    id_role_list  integer not null
        constraint "RoleListAssignment_ListRole_id_role_list_fk"
            references "ListRole"
);

create table "MarkedGift"
(
    id_mark          serial
        constraint "MarkedGift_pk"
            primary key,
    id_user          integer not null
        constraint "MarkedGift_User_id_user_fk"
            references "User",
    id_gift          integer not null
        constraint "MarkedGift_Gift_id_gift_fk"
            references "Gift",
    is_gift_selected boolean default false,
    selection_date   date,
    id_list          integer not null
        constraint "MarkedGift_List_id_list_fk"
            references "List"
);

create table "SocialLink"
(
    id_social_link serial
        constraint "SocialLink_pk"
            primary key,
    id_user        integer not null,
    link           varchar(255)
);

create table "InvitedToList"
(
    id_invitation serial
        constraint "InvitedToList_pk"
            primary key,
    id_user       integer not null
        constraint "InvitedToList_User_id_user_fk"
            references "User",
    id_list       integer not null
        constraint "InvitedToList_List_id_list_fk"
            references "List",
    id_role_list  integer not null
);

create table "FriendRequest"
(
    id_request  serial
        constraint "FriendRequest_pk"
            primary key,
    id_sender   integer               not null
        constraint "FriendRequest_User_id_user_fk"
            references "User",
    id_receiver integer               not null
        constraint "FriendRequest_User_id_user_fk2"
            references "User",
    status      boolean default false not null
);