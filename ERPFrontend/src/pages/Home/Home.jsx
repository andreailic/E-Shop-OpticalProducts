import React from 'react';
import { Link } from 'react-router-dom';

export default function Home() {
  const containerStyle = {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'space-between',
    alignItems: 'center',
    backgroundImage: 'url(/landing.jpg)',
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    height: '100vh',
    paddingTop: '5rem',
  };

  const titleStyle = {
    textAlign: 'center',
    color: 'yellow',
    fontSize: '5rem',
    marginBottom: '1rem',
  };

  const descriptionStyle = {
    textAlign: 'center',
    color: 'black',
    fontSize: '1.5rem',
    margin: '0 2rem',
  };

  const buttonStyle = {
    padding: '1rem 2rem',
    fontSize: '1.2rem',
    backgroundColor: 'yellow',
    color: 'black',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
  };

  return (
    <div style={containerStyle}>
      <h1 style={titleStyle}>Optics Store</h1>
      <p style={descriptionStyle}>
        Dobrodošli u Optics Store, Vašu destinaciju za visokokvalitetne naočare i optičke proizvode.
      </p>
      <Link to="/product" style={buttonStyle}>
        Ponuda
      </Link>

      <div style={{ flex: '1' }} />

      <section id="about" style={{ marginTop: '3rem' }}>
        <div className="container">
          <h2 className="section-title">O nama</h2>
          <p className="section-description" style={{ color: 'yellow' }}>
            Mesto gde ćete pronaći savršene naočare koje će odgovarati vašem stilu i potrebama. Sa širokim spektrom brendova i modela, uvereni smo da ćete pronaći nešto što vam se sviđa.
          </p>
          <p className="section-description" style={{ color: 'yellow' }}>
            Posetite našu prodavnicu i istražite našu ponudu. Nudimo kvalitetne proizvode i brzu dostavu. Vaše zadovoljstvo nam je najvažnije.
          </p>
        </div>
      </section>
    </div>
  );
}
