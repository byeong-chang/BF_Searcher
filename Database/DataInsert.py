import mysql.connector
import pandas as pd
# MySQL 서버에 연결
mydb = mysql.connector.connect(
    host="localhost",  # 호스트 이름
    user="root",  # 사용자 이름
    password="qwer1234",  # 비밀번호
    database="bf_searcher"  # 데이터베이스 이름
)


# 데이터베이스 커서 생성
mycursor = mydb.cursor()

# CSV 파일을 읽어 데이터프레임으로 변환
locationCategory = pd.read_csv('./../Data/LocationCategory.csv',index_col = 0)
disabledCategory = pd.read_csv('./../Data/disabledCategory.csv',index_col = 0)
hobbyCategory = pd.read_csv('./../Data/hobbyCategory.csv',index_col = 0)
location = pd.read_csv('./../Data/Location.csv',index_col = 0)


# 데이터프레임을 반복하면서 데이터 삽입
for index, row in locationCategory.iterrows():
    sql = "INSERT INTO location_category  VALUES (%s, %s)"
    val = [index+1,row[0]]  # 각 열에 대한 값을 채워넣습니다.
    mycursor.execute(sql, val)


# # 데이터프레임을 반복하면서 데이터 삽입
for index, row in disabledCategory.iterrows():
    sql = "INSERT INTO disabled_category VALUES (%s, %s)"
    val = (index+1,row[0])  # 각 열에 대한 값을 채워넣습니다.
    mycursor.execute(sql, val)


# # 데이터프레임을 반복하면서 데이터 삽입
for index, row in hobbyCategory.iterrows():
    sql = "INSERT INTO hobby_category VALUES (%s, %s)"
    val = (index+1,row[0])  # 각 열에 대한 값을 채워넣습니다.
    mycursor.execute(sql, val)

# 변경 사항 저장
mydb.commit()

# 연결 종료
mydb.close()

# MySQL 서버에 연결
mydb = mysql.connector.connect(
    host="localhost",  # 호스트 이름
    user="root",  # 사용자 이름
    password="qwer1234",  # 비밀번호
    database="bf_searcher"  # 데이터베이스 이름
)
# 데이터베이스 커서 생성
mycursor = mydb.cursor()


# # 데이터프레임을 반복하면서 데이터 삽입
for index, row in location.iterrows():
    sql = "INSERT INTO location VALUES (%s, %s, %s,%s, %s, %s,%s, %s, %s,%s, %s, %s,%s, %s, %s,%s, %s, %s,%s, %s, %s,%s)"
    val = (index+1, row['시설명'],row['분류'],row['시군구 명칭'],row['위도'], row['경도'], row['지번주소'], row['전화번호'], row['홈페이지'], row['휴무일'],
        row['운영시간'], row['무료주차 가능여부'], row['유료주차 가능여부'], row['장애인용 출입문'], row['휠체어 대여 가능 여부'],
        row['장애인 화장실 유무'], row['장애인 전용 주차장 여부'], row['대형주차장 가능여부'], row['시각장애인 안내견 동반 가능 여부'],
        row['점자 가이드 여부'], row['오디오 가이드(한국어)'],0)  # 각 열에 대한 값을 채워넣습니다.
    mycursor.execute(sql, val)

# # # 데이터프레임을 반복하면서 데이터 삽입
# for index, row in location.iterrows():
#     sql = "INSERT INTO location VALUES (%s, %s, %s,%s, %s, %s,%s, %s, %s,%s, %s, %s,%s, %s, %s,%s, %s, %s,%s, %s, %s,%s)"
#     val = (row['오디오 가이드(한국어)'], row['대형주차장 가능여부'],row['점자 가이드 여부'], row['장애인용 출입문'],row['무료주차 가능여부'],row['시각장애인 안내견 동반 가능 여부'],
#         row['위도'], row['경도'],row['유료주차 가능여부'],  row['장애인 전용 주차장 여부'],0,row['장애인 화장실 유무'],row['휠체어 대여 가능 여부'],
#         row['분류'],index+1,row['시군구 명칭'],row['지번주소'],row['휴무일'],row['홈페이지'], row['시설명'],row['전화번호'], row['운영시간'])
#     mycursor.execute(sql, val)
# 변경 사항 저장
mydb.commit()

# 연결 종료
mydb.close()