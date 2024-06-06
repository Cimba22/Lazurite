FROM node:20.11.1 AS angular-builder
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm ci
RUN npm install -g @angular/cli@16.1.4
COPY . .
RUN npm run build --configuration=production
CMD ["ng", "serve", "--host", "0.0.0.0"]

