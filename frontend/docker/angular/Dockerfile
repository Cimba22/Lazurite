#FROM node:latest as builder
#
#WORKDIR /app
#
#COPY package*.json ./
#
#RUN npm install
#
#COPY .. .
#
#RUN npm run build --prod
#
#
#FROM nginx:alpine
#
#COPY --from=builder /app/dist/frontend /usr/share/nginx/html
#
#EXPOSE 80
