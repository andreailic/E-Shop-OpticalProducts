
export default function Home() {

    const containerStyle = {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'flex-start',
        backgroundImage: 'url(/landing.jpg)',
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        height: '95vh',
      };
    
      const titleStyle = {
        textAlign: 'center',
        color: '#17a2b8',
        fontSize: '5rem',
      };
    
      return (
        <div style={containerStyle}>
          <h1 style={titleStyle}>Optics Store</h1>
        </div>
      );
}