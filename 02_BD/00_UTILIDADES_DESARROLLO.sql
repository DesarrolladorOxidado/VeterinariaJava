-- =====================================================
-- UTILIDADES PARA DESARROLLO
-- No ejecutar en producción.
-- =====================================================

-- Reinicia la secuencia del IDENTITY de DUENIOS.
ALTER TABLE duenios
ALTER COLUMN id_duenio
RESTART WITH 2;

-- Reinicia la secuencia de VETERINARIOS (ejemplo).
-- ALTER TABLE veterinarios
-- ALTER COLUMN id_veterinario
-- RESTART WITH 1;