package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import androidx.collection.ArraySet;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.SignInOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class ClientSettings {
    @Nullable
    private final Account zaa;
    private final Set<Scope> zab;
    private final Set<Scope> zac;
    private final Map<Api<?>, zaa> zad;
    private final int zae;
    private final View zaf;
    private final String zag;
    private final String zah;
    private final SignInOptions zai;
    private final boolean zaj;
    private Integer zak;

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static final class zaa {
        public final Set<Scope> zaa;

        public zaa(Set<Scope> set) {
            Preconditions.checkNotNull(set);
            this.zaa = Collections.unmodifiableSet(set);
        }
    }

    public static ClientSettings createDefault(Context context) {
        return new GoogleApiClient.Builder(context).buildClientSettings();
    }

    public ClientSettings(Account account, Set<Scope> set, Map<Api<?>, zaa> map, int i, View view, String str, String str2, SignInOptions signInOptions) {
        this(account, set, map, i, view, str, str2, signInOptions, false);
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static final class Builder {
        @Nullable
        private Account zaa;
        private ArraySet<Scope> zab;
        private int zac = 0;
        private String zad;
        private String zae;
        private SignInOptions zaf = SignInOptions.zaa;

        public final Builder zaa(@Nullable Account account) {
            this.zaa = account;
            return this;
        }

        public final Builder zaa(Collection<Scope> collection) {
            if (this.zab == null) {
                this.zab = new ArraySet<>();
            }
            this.zab.addAll(collection);
            return this;
        }

        public final Builder setRealClientPackageName(String str) {
            this.zad = str;
            return this;
        }

        public final Builder zaa(String str) {
            this.zae = str;
            return this;
        }

        public final ClientSettings build() {
            return new ClientSettings(this.zaa, this.zab, (Map<Api<?>, zaa>) null, 0, (View) null, this.zad, this.zae, this.zaf, false);
        }
    }

    public ClientSettings(@Nullable Account account, Set<Scope> set, Map<Api<?>, zaa> map, int i, View view, String str, String str2, SignInOptions signInOptions, boolean z) {
        this.zaa = account;
        this.zab = set == null ? Collections.emptySet() : Collections.unmodifiableSet(set);
        this.zad = map == null ? Collections.emptyMap() : map;
        this.zaf = view;
        this.zae = i;
        this.zag = str;
        this.zah = str2;
        this.zai = signInOptions;
        this.zaj = false;
        HashSet hashSet = new HashSet(this.zab);
        for (zaa zaa2 : this.zad.values()) {
            hashSet.addAll(zaa2.zaa);
        }
        this.zac = Collections.unmodifiableSet(hashSet);
    }

    @Deprecated
    @Nullable
    public final String getAccountName() {
        Account account = this.zaa;
        if (account != null) {
            return account.name;
        }
        return null;
    }

    @Nullable
    public final Account getAccount() {
        return this.zaa;
    }

    public final Account getAccountOrDefault() {
        Account account = this.zaa;
        if (account != null) {
            return account;
        }
        return new Account("<<default account>>", AccountType.GOOGLE);
    }

    public final int getGravityForPopups() {
        return this.zae;
    }

    public final Set<Scope> getRequiredScopes() {
        return this.zab;
    }

    public final Set<Scope> getAllRequestedScopes() {
        return this.zac;
    }

    public final Map<Api<?>, zaa> zaa() {
        return this.zad;
    }

    @Nullable
    public final String getRealClientPackageName() {
        return this.zag;
    }

    @Nullable
    public final String zab() {
        return this.zah;
    }

    @Nullable
    public final View getViewForPopups() {
        return this.zaf;
    }

    public final SignInOptions zac() {
        return this.zai;
    }

    @Nullable
    public final Integer zad() {
        return this.zak;
    }

    public final void zaa(Integer num) {
        this.zak = num;
    }

    public final Set<Scope> getApplicableScopes(Api<?> api) {
        zaa zaa2 = this.zad.get(api);
        if (zaa2 == null || zaa2.zaa.isEmpty()) {
            return this.zab;
        }
        HashSet hashSet = new HashSet(this.zab);
        hashSet.addAll(zaa2.zaa);
        return hashSet;
    }

    public final boolean zae() {
        return this.zaj;
    }
}
