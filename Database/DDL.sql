drop database bf_searcher;
Create database bf_searcher;
use bf_searcher;
-- 카테고리 테이블 3개 생성
CREATE TABLE Hobby_category (
    id BIGINT PRIMARY KEY,
    hobby VARCHAR(255)
);
CREATE TABLE Location_category (
    id BIGINT PRIMARY KEY,
    location VARCHAR(255)
);
CREATE TABLE Disabled_category (
    id BIGINT PRIMARY KEY,
    disabled VARCHAR(255)
);

-- 데이터 담을 주요 테이블인 Location 테이블 생성 
CREATE TABLE Location (
    id BIGINT PRIMARY KEY,
    location_name VARCHAR(255),
    hobby_category_id BIGINT,
    location_category_id BIGINT,
    latitude DOUBLE,
    longitude DOUBLE,
    address VARCHAR(255),
    phone_number VARCHAR(20),
    homepage VARCHAR(255),
    closed_day VARCHAR(255),
    runtime VARCHAR(255),
    free_park int,
    paid_park int,
    door_for_disabled int,
    wheelchair_rental int,
    toilet_for_disabled int,
    park_for_disabled int,
    big_park int,
    guide_dog_permitted int,
    braille_guide int,
    audio_guide int,
    star_rating DOUBLE DEFAULT 0,
    FOREIGN KEY (hobby_category_id) REFERENCES Hobby_category(id),
    FOREIGN KEY (location_category_id) REFERENCES Location_category(id)
);

-- User 테이블을 만들기 위해 필요한(정확히는 취미, 좋아요 누른 장소 들이 중복허용이라 제1정규화 도메인 원자성? 에 의해 발생하는 테이블)테이블 생성
-- 을 하는게 정석인데 복수 허용이다 보니 결국 정규화 안하고 자바로 짜는게 나아보임

-- User 데이터를 담을 User 테이블 생성
CREATE TABLE User (
    id BIGINT PRIMARY KEY,
    hobby TEXT,
    Like_location TEXT,
    location_category_id BIGINT,
    disabled_category_id BIGINT,
    username VARCHAR(255),
    email VARCHAR(255),
    userId VARCHAR(255),
    passwd VARCHAR(255),
    disabled BIGINT,
    FOREIGN KEY (location_category_id) REFERENCES Location_category(id),
    FOREIGN KEY (disabled_category_id) REFERENCES Disabled_category(id)
);
-- Matching 기록을 남길 MatchingLog 테이블들 생성

CREATE TABLE Matching_log (
    id BIGINT PRIMARY KEY,
    volunteer_user_id BIGINT,
    matching_user_id BIGINT,
    matching_result VARCHAR(255),
    FOREIGN KEY (volunteer_user_id) REFERENCES User(id),
    FOREIGN KEY (matching_user_id) REFERENCES User(id)
);

-- 자원봉사자 모집하는 글을 저장하는 Recruitment 테이블 생성
CREATE TABLE Recruitment (
    id BIGINT PRIMARY KEY,
    location_id BIGINT,
    user_id BIGINT,
    date DATE,
    content TEXT,
    flag INT,
    FOREIGN KEY (location_id) REFERENCES Location(id),
    FOREIGN KEY (user_id) REFERENCES User(id)
);

-- chatlog인데 솔직히 아직 뭐쓸지 모르니 아무렇게나 만듬
CREATE TABLE Chat_log (
    id BIGINT PRIMARY KEY,
    volunteer_user_id BIGINT,
    matching_user_id BIGINT,
    recruitment_id BIGINT,
    data TEXT,
    flag INT,
    FOREIGN KEY (volunteer_user_id) REFERENCES User(id),
    FOREIGN KEY (matching_user_id) REFERENCES User(id),
    FOREIGN KEY (recruitment_id) REFERENCES Recruitment(id)
);
use bf_searcher;
select * from location;
select * from location_category;
select * from hobby_category;
select * from disabled_category;
select count(*) from location;