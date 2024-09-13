ALTER TABLE videos ADD COLUMN categoriaId NUMERIC;
UPDATE videos SET categoriaId = 1;