-- 코드를 입력하세요
SELECT O.ANIMAL_ID, O.NAME
FROM ANIMAL_OUTS AS O
LEFT JOIN ANIMAL_INS AS I
ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE O.DATETIME IS NOT NULL 
AND I.DATETIME IS NULL
ORDER BY O.ANIMAL_ID