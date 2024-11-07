export const environment = {
  production: true,
  apiUrl: window["env"]["apiUrl"] || 'https://flagexpress-api.azurewebsites.net',
  debug: window["env"]["debug"] || false
};
