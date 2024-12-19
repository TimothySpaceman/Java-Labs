FROM postgres:latest

ENV POSTGRES_DB=store
ENV POSTGRES_USER=javalabs
ENV POSTGRES_PASSWORD=javalabs

EXPOSE 5432
