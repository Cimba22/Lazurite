# Этап сборки Angular приложения
FROM node:20.11.1 AS angular-builder
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm ci
RUN npm install -g @angular/cli
COPY . .
RUN npm run build --configuration=production

# Финальный этап сборки
FROM nginx:alpine
COPY ./docker/nginx/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=angular-builder /app/dist/frontend /usr/share/nginx/html


EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
