INSERT INTO consultas(
fecha_consulta,
motivo_consulta,
diagnostico_consulta,
tratamiento_consulta,
observaciones_consulta,
veterinario_consulta,
historia_clinica_consulta
)
VALUES(
'2026-09-02 15:25:00',
'Control semestral',
'Sin diagnóstico',
'Sin tratamiento',
'Sin observaciones',
1,
1
)

UPDATE historias_clinicas
SET fecha_actualizacion_historia_clinica = '2026-09-02 15:25:00'
WHERE id_historia_clinica = 1

SELECT hc.fecha_actualizacion_historia_clinica, c.fecha_consulta
FROM historias_clinicas hc
INNER JOIN consultas c ON hc.id_historia_clinica = c.historia_clinica_consulta

