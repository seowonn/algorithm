-- 코드를 입력하세요
SELECT CATEGORY, SUM(BOOK_SALES.SALES) AS TOTAL_SALES FROM BOOK_SALES
JOIN BOOK AS B ON B.BOOK_ID = BOOK_SALES.BOOK_ID
WHERE YEAR(SALES_DATE) = 2022 AND MONTH(SALES_DATE) = 1
GROUP BY B.CATEGORY
ORDER BY CATEGORY;