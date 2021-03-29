module.exports = {
  plugins: {
    // tailwindcss: {},     // old
    '@tailwindcss/jit': {}, // new - jit css compiler
    autoprefixer: {},
    cssnano: process.env.NODE_ENV == 'production' ? {} : false
  }
}
