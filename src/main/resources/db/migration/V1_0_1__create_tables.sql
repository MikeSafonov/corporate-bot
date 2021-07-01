ALTER TABLE "public"."usefulness" ADD COLUMN "id" serial NOT NULL;
ALTER TABLE "public"."usefulness" ADD CONSTRAINT "usefulness_pkey" PRIMARY KEY ("id");
