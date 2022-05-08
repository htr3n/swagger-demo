// See: https://stackoverflow.com/a/71210264/339302
const UrlMutatorPlugin = (system) => ({
  rootInjects: {
    setServer: (server) => {
      const jsonSpec = system.getState().toJSON().spec.json;
      const servers = [{url: server}];
      const newJsonSpec = Object.assign({}, jsonSpec, {servers});
      return system.specActions.updateJsonSpec(newJsonSpec);
    }
  }
});

window.onload = function () {
  const base_url = window.location.origin + window.location.pathname;
  window.ui = SwaggerUIBundle({
    url: `${base_url}api/openapi.json`,
    dom_id: '#swagger-ui',
    deepLinking: true,
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    plugins: [
      SwaggerUIBundle.plugins.DownloadUrl,
      UrlMutatorPlugin
    ],
    layout: "StandaloneLayout",
    onComplete: () => {
      const base_url_no_ending_slash = base_url.replace(/\/$/, '');
      window.ui.setServer(base_url_no_ending_slash);
    }
  });
};
