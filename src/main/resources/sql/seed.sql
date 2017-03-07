-- name: insert_race
INSERT INTO race (id) VALUES (:id);

-- name: select_all_races
SELECT * FROM race;

-- name: delete_race
DELETE FROM race WHERE id = :id;
