import { useEffect, useState } from "react";

function App() {
    const [filmes, setFilmes] = useState([]);

    const usuarioId = new URLSearchParams(window.location.search).get("usuarioId");

    useEffect(() => {
        console.log("UsuarioId:", usuarioId);
        fetch(`http://localhost:8082/filmes/api?usuarioId=${usuarioId}`)
            .then((response) => response.json())
            .then((data) => setFilmes(data))
            .catch((error) => console.error(error));
    }, [usuarioId]);

  const generos = {};

  filmes.forEach((filme) => {
    generos[filme.genero] = (generos[filme.genero] || 0) + 1;
  });

  return (
      <div style={{ padding: "30px" }}>
        <h1>🎬 ScreenMatch Dashboard</h1>

          <a
              href={`http://localhost:8082/filmes?usuarioId=${usuarioId}`}
              style={{
                  display: "inline-block",
                  marginBottom: "20px",
                  padding: "10px 15px",
                  backgroundColor: "#198754",
                  color: "white",
                  textDecoration: "none",
                  borderRadius: "5px",
              }}
          >
              ← Voltar para Filmes
          </a>

        <h2>Bem-vindo ao ScreenMatch</h2>

        <p>Frontend React conectado aos microserviços.</p>

        <div
            style={{
              border: "1px solid #ccc",
              padding: "20px",
              marginTop: "20px",
              borderRadius: "10px",
            }}
        >
          <h3>Total de Filmes</h3>
          <h1>{filmes.length}</h1>
        </div>

        <div
            style={{
              border: "1px solid #ccc",
              padding: "20px",
              marginTop: "20px",
              borderRadius: "10px",
            }}
        >
          <h3>Filmes por Gênero</h3>

          {Object.entries(generos).map(([genero, quantidade]) => (
              <p key={genero}>
                <strong>{genero}</strong>: {quantidade}
              </p>
          ))}
        </div>

        <div style={{ marginTop: "30px" }}>
          <h2>Meus Filmes</h2>

          {filmes.map((filme) => (
              <div
                  key={filme.id}
                  style={{
                    border: "1px solid gray",
                    padding: "15px",
                    marginBottom: "10px",
                    borderRadius: "8px",
                  }}
              >
                <h3>{filme.titulo}</h3>
                <p>{filme.descricao}</p>
                <strong>{filme.genero}</strong> - {filme.ano}
              </div>
          ))}
        </div>
      </div>
  );
}

export default App;