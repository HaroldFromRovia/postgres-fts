-- Ищем слова, у которых в описании есть слово lost
-- EXPLAIN ANALYSE
SELECT word
FROM dictionary
WHERE to_tsvector(definition) @@ to_tsquery('lost');

-- Ищем id и ранги слов, в которых есть слово Hell
-- EXPLAIN ANALYSE
SELECT id, ts_rank(to_tsvector(dictionary.definition), plainto_tsquery('Hell'))
FROM dictionary
WHERE to_tsvector(dictionary.definition) @@ plainto_tsquery('Hell')
ORDER BY ts_rank(to_tsvector(dictionary.definition), plainto_tsquery('Hell')) DESC;

SELECT to_tsvector(dictionary.definition)
FROM dictionary;

-- Функция COALESCE возвращает первый попавшийся аргумент, отличный от NULL
-- Ищем
SELECT id, ts_rank(to_tsvector(coalesce(dictionary.definition, '')), to_tsquery('hell'), 32) as rank
FROM dictionary
ORDER BY rank DESC
    LIMIT 5;

-- ndoc - число вхождений, nentry - число документов (ts_vector-ов)
SELECT *
FROM ts_stat(
        'SELECT to_tsvector(definition) FROM dictionary'
    )
ORDER BY nentry DESC, ndoc DESC, word
    LIMIT 5;

SELECT ts_headline('english', '', to_tsquery('head'));

SELECT ts_headline('english',
                   definition,
                   to_tsquery('english', 'query & similarity'),
                   'MaxFragments=0, MinWords=5, MaxWords=9')
FROM dictionary
WHERE to_tsvector(definition) @@ plainto_tsquery('ascendance')
ORDER BY ts_rank(to_tsvector(definition), plainto_tsquery('ascendance'));
INSERT INTO dictionary(word, pos, definition) VALUES ('Absolute Radiance', 'A.', 'Ascended god of the past. Ascended radiance');
