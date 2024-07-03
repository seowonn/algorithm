-- 코드를 입력하세요
SELECT YEAR(SALES_DATE) AS YEAR, 
    MONTH(SALES_DATE) AS MONTH, 
    GENDER, 
    COUNT(DISTINCT U.USER_ID) AS USERS
FROM USER_INFO AS U
JOIN ONLINE_SALE
ON U.USER_ID = ONLINE_SALE.USER_ID
WHERE U.GENDER IS NOT NULL
GROUP BY YEAR, MONTH, GENDER
ORDER BY YEAR, MONTH, GENDER